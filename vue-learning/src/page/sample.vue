<template>
    <div>
        {{ message }}
        <Button v-show="flag" type="warning" @click="changeValue">Warning</Button>
        <Card>
            发生的故事对方是否到货
        </Card>
    </div>
</template>
<script>
export default {
  data () {
    return {
      message: 'hello!',
      flag: true,

    }
  },
  methods: {
    changeValue () {
      this.flag = !this.flag
      alert(Ahri.data.name)
      this.message = this.message.substring(0, 5) + Ahri.data.name
    },
  }
}
</script>
