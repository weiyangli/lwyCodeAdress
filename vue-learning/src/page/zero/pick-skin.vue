<template>
    <div class="pick-skin">
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
        }
    },
    methods: {
      pick() {
        if(isNaN(this.pickCount)){
          this.$Message.warning('只能填写数字');
          return;
        } else if (this.pickCount > 100){
          this.$Message.warning('每次抽奖次数不超过100次');
          return;
        }
        // 输入数据校验通过，开始抽取皮肤
      }

    }
}
</script>
<style lang="scss">
    .pick-skin {
        height: 100%;

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
</style>
