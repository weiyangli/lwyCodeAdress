<template>
    <div class="prizes-list">
        <Modal
        v-model="pushPickModal"
        @on-ok="pushPick">
            <div>
                抽奖时间为:<DatePicker v-model="sj" type="datetime" placeholder="Select time"/>
            </div>
       </Modal>
        <!-- 添加奖品-->
        <Modal ref="modal" v-model="addPrizeModal" :loading="publishing" title="添加奖品" footer-hide class="prize-modal">
            <Form ref="prizeForm" :rules="rules" :model="prizeForm">
                <FormItem prop="prizeName" required>
                    <Input v-model="prizeForm.prizeName" :rows="4" placeholder="例如：Iphonex" />
                </FormItem>
                <FormItem prop="prizeImg" required>
                   <Upload v-model="prizeForm.prizeImg" 
                   :format="format" 
                   :on-success="onSuccess"
                   action="/api/upload/file">
                       <Button icon="ios-cloud-upload-outline">上传图片</Button><br/>
                       <span>支持格式:jpg,jpeg,png</span>
                   </Upload>
                </FormItem>
                <FormItem prop="prizePrice" required>
                    <InputNumber
                        v-model="prizeForm.prizePrice"
                       :formatter="value => `￥ ${value}`.replace(/B(?=(d{3})+(?!d))/g, ',')"
                       :parser="value => value.replace(/$s?|(,*)/g, '')"/>
                </FormItem>
            </Form>
            <div style="display: flex; justify-content: flex-end">
                <Button style="margin-right: 10px" @click="cancel">取消</Button>
                <Button type="primary" @click="publish">确定</Button>
            </div>
        </Modal>
        <Input placeholder="Enter text" style="width: auto;margin-right:15px">
            <Icon slot="suffix" type="ios-search" />
        </Input>
        <Button type="success" ghost class="pick-button" @click="addPrize">添加奖品</Button>
        <Table :columns="columns" :data="prizes"></Table>
    </div>
</template>
<script>
export default {
    data () {
        return {
            pushPrise:{},
            sj:'',
            pushPickModal: false,
            format:['jpg','jpeg','png'],
            publishing: false,
            prizeForm: {
                id:0,
                prizeName:'',
                prizePrice:0,
                prizeImg:'',
                enable:0,
                bePickNum:0,
                pickTime:''
            },
            rules: {
                prizeName : [{required: true, message: '请输入奖品名称', trigger: 'blur'}],
                prizePrice : [{required: true, min:1, type:'number', message: '请输入价格,必需大于0', trigger: 'blur'}],
                prizeImg : [{required: true, message: '请上传图片', trigger: 'blur'}],
            },
            addPrizeModal:false,
            columns: [
                {
                    title: '奖品名称',
                    key: 'prizeName',
                    width:150,
                },
              {
                title: "图片",
                key: 'prizeImg',
                columns: {
                  'width':'50px'
                },
                width:150,
                render: (h, params) => {
                  return h('div', [
                    h('img', {
                      attrs: {
                        src: "/api/image/preview?fileUrl="+params.row.prizeImg
                      },
                      style: {
                        width: '40px',
                        height: '40px'
                      }
                    }),
                  ]);
                }
              },
              {
                    title: '奖品价格',
                    key: 'prizePrice',
                    width:100,
                },
                {
                    title: '被设置为奖品次数',
                    key: 'bePickNum',
                    width:150,
                },
                 {
                    title: '创建时间',
                    key: 'createTime',
                    width:150,
                },
                 {
                        title: '设置',
                        key: 'action',
                        align: 'center',
                        render: (h, params) => {
                           if (params.row.enable == 0) {
                                return (
                                    <div class='remark-box'>
                                        <i-button type='primary' onClick={()=>{this.pushToPick(params.row)}}>设置为抽奖奖品</i-button>
                                    </div>
                                )
                            } else {
                                return (
                                    <div class='remark-box'>
                                        <i-button disabled>等待开奖</i-button>
                                    </div>
                                )
                            }
                        }
                 },
                 {
                        title: '历史记录',
                        key: 'action',
                        align: 'center',
                        render: (h, params) => {
                            if (params.row.bePickNum > 0) { 
                                return (
                                    <div>
                                    <i-button type='success'>查看中奖历史</i-button>
                                </div>
                                )
                            } else {
                            return (
                                    <div class='remark-box'>
                                        <i-button disabled>未设置为奖品</i-button>
                                    </div>
                                )
                            }
                            
                        }
                 }
            ],
            prizes: [ ],
        }
    },

    created() {
        this.findPrizes()
    },
    activated() {
        this.findPrizes()
    },
    methods: {
        // 图片上传成功调用
        onSuccess(result, fileList) {
            if (result) {
                this.prizeForm.prizeImg = result;
            }
        },
        // 查询英雄列表
        findPrizes(){
          this.prizes = [];
          let promise = this.getPromise('/api/prizes');
          promise.
          then(data => {
            this.prizes = JSON.parse(data);
          }).then(data => {
            console.log(data);
          }).catch(err => {
            console.log(err);
          });
        },
      getPromise(url) {
        let p = new Promise((resolve,reject) => {
        let xhr = new XMLHttpRequest;
      xhr.open('get', url, true);
      xhr.onreadystatechange = () => {
        if (xhr.status == 200 && xhr.readyState===4 ){
          resolve(xhr.responseText);
        }
      };
      xhr.onerr = function () {
        reject();
      }
      xhr.send(null);
    });
    return p;
  },
    addPrize() {
       // 启动弹窗
       this.addPrizeModal = true;
    },
    cancel() {
        this.$refs.modal.close();
    },
        publish() {
          let adminInfo=JSON.parse(JSON.stringify(this.prizeForm));
          this.$refs.prizeForm.validate((valid) => {
                if (valid) {
                    this.$http.post('/api/add/prize', adminInfo,)
                     .then(function (response) {
                        if(response.data.code === 200) {
                            this.findPrizes();
                        }
                   })
                     .catch(function (error) {
                      console.log(error);
                   });
                } else {
                    this.$Message.warning('参数填写不完整');
            }
        });
          
        this.cancel();
    },
    // 设置奖品为抽奖奖品
    pushToPick (data) {
        this.pushPickModal = true;
        this.pushPrise = data;
    },
    pushPick() {
        this.pushPrise.pickTime = this.sj;
        let adminInfo=JSON.parse(JSON.stringify(this.pushPrise));
        this.$http.post('/api/add/prize', adminInfo,)
                     .then(function (response) {
                     console.log(response);
                   })
                     .catch(function (error) {
                      console.log(error);
                   });
                   this.$Message.success('设置成功!');
    }

    },

}
</script>
<style lang="scss">
  .prizes-list {
       padding: 16px;

      .pick-button {
        margin-bottom: 10px;

        &:hover {
          border: 1px solid orange;
          color: orange;
        }
      }
  }
</style>
