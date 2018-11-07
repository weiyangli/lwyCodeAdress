<template>
    <div class="centered-x">
        <PdfPlayer url="http://192.168.10.240:8888/preview/file/data/2018-09-03/221142963827245056.ppt"></PdfPlayer>
    </div>
</template>
<script>
import PdfPlayer from '@/components/PdfPlayer';

export default {
    components: {
        PdfPlayer,
    },
};
</script>
<style lang="scss" scoped>
.centered-x {
    flex: 1;
    width: 900px;
    margin: 0 auto;
    position: relative;
    background: azure;
}
</style>

