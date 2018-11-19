<template>
    <div class="prizes-list">
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
        <Button type="success" ghost class="pick-button" @click="addPrize">添加奖品</Button>
        <Table :columns="columns" :data="prizes"></Table>
    </div>
</template>
<script>
export default {
    data () {
        return {
            format:['jpg','jpeg','png'],
            publishing: false,
            prizeForm: {
                prizeName:'',
                prizePrice:0,
                prizeImg:'123',
                Enable:0,
                bePickNum:0,
                pickTime:''
            },
            rules: {
                prizeName : [{required: true, message: '请输入奖品名称', trigger: 'blur'}],
                prizePrice : [{required: true, type:'number', message: '请输入价格', trigger: 'blur'}],
                prizeImg : [{required: true, message: '请请上传图片', trigger: 'blur'}],
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
                        src: params.row.prizeImg
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
                        title: '操作',
                        key: 'action',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.show(params.index)
                                        }
                                    }
                                }, '设置为抽奖奖品'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.remove(params.index)
                                        }
                                    }
                                }, '查看以往中奖名单')
                            ]);
                        }
                 }
            ],
            prizes: [ ],
        }
    },

    created() {
        this.findPrizes()
    },
    methods: {
        // 图片上传成功调用
        onSuccess(result, file, fileList) {
            alert(1)
                if (result.success) {
                    alert(1)
                    const fileFromServer = result.data; // 上传成功后服务器返回的文件信息
                    console.log(fileFromServer)
                } else {
                     alert(2)
                    console.warn(result.data);
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
                     console.log(response);
                   })
                     .catch(function (error) {
                      console.log(error);
                   });
                   this.findPrizes();
                }
          });
          this.$refs.modal.close();
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
