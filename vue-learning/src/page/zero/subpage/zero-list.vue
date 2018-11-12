<template>
    <div class="zero-list">
        <Button type="success" ghost class="pick-button" @click="goPick">皮肤抽取</Button>
        <Table :columns="columns" :data="zero"></Table>
    </div>
</template>
<script>
export default {
    data () {
        return {
            columns: [
                {
                    title: '英雄名',
                    key: 'key'
                },
              {
                title: "图片",
                key: 'image',
                columns: {
                  'width':'50px'
                },
                render: (h, params) => {
                  return h('div', [
                    h('img', {
                      attrs: {
                        src: params.row.image
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
                    title: '中文名',
                    key: 'name'
                },
                {
                    title: '昵称',
                    key: 'title'
                },
            ],
            zero: [ ],
        }
    },

    created() {
        this.findZeros()
    },
    methods: {
        // 查询英雄列表
        findZeros(){
          var  zeros = []
          let promise = this.getPromise('/api/demo/test/1');
          promise.
          then(data => {
            zeros = JSON.parse(data);
            for(let zero of zeros) {
                // 处理图片
                let image = JSON.parse(zero.image);
                let full = "../../../static/img/"+image.full;
                zero.image = full
            }
            this.zero = zeros;
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
    goPick() {
       this.$router.push({
                    name: 'pick-skin'
                });
    }
    },
}
</script>
<style lang="scss">
  .zero-list {
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
