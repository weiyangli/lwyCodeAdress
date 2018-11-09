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
      // this.flag = !this.flag
      // alert(Ahri.data.name)
      // this.message = this.message.substring(0, 5) + Ahri.data.name
      this.pickModal = true;
          let promise = this.getPromise('/');
          promise.
          then(data => {
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
  }
}
</script>
