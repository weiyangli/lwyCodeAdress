<template>
    <div class="pick-skin">
      <Modal v-model="pickModal" title="你获得了以下奖励" width="800px" class="pick-modal">
            <div v-for="skin in skins" :key="skin.id" class="skin-box">
                <img :src="skin.skinImg" class="skin-img"/>
                <span>{{ skin.skinName }}</span>
                <span>{{ skin.skinLevel }} - {{ skin.skinPrice }}</span>
            </div>
      </Modal>
            <h4>幸运召唤师</h4>
            <div class="random-pick">
                <li style="margin-bottom: 20px">输入抽取次数随机抽取皮肤</li>
                <div class="pick-box">
                    <Input v-model="pickCount"/>
                    <Button type="primary" class="pick-button" @click="pick">开始</Button>
                </div>
            </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            pickCount: 5,
            pickModal: false,
            skins: []
        }
    },
    methods: {
      pick() {
        if(isNaN(this.pickCount)){
          this.$Message.warning('只能填写数字');
          return;
        } else if (this.pickCount > 500){
          this.$Message.warning('每次抽奖次数不超过500次');
          return;
        }
        // 输入数据校验通过，开始抽取皮肤
        this.pickModal = true;
          let promise = this.getPromise('/api/lol/pick/skin?count='+this.pickCount);
          promise.
          then(data => {
            this.skins = JSON.parse(data);
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
  }

    },
}
</script>
<style lang="scss">
    .pick-skin {
        height: 100%;
        .pick-modal {
            display: flex;
            flex-direction: column;
        }
        .random-pick {
            display: flex;
            flex-direction: column;
            align-items: center;

            .pick-box {
                display: flex;
                flex-direction: row;

                .pick-button {
                    margin-left: 15px;
                    &:hover {
                        border: 1px solid #5cadff;
                        background-color: #5cadff;
                    }
                }
            }
        }
    }
    .ivu-modal-body {
                .skin-box {
                    display: flex;
                    flex-direction: column;
                    span {
                        font-size: 15px;
                    }

                    .skin-img {
                        width:30px;
                        height:50px;
                    }
                }
            }
</style>
