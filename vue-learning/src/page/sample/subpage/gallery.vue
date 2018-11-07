<template>
    <div>
        <!-- [5] 点击页面上的图片后显示大图的 Gallery -->
        <Gallery :images="images" :index="galleryImageIndex" @close="galleryImageIndex = null"/>

        <!-- [4] 页面上显示图片，点击后显示预览大图 -->
        <div class="images">
            <img v-for="(image, index) in images" :src="image" :key="index" @click="galleryImageIndex = index">
        </div>
    </div>
</template>

<script>
import Gallery from 'vue-gallery'; // [1] 引入组件

export default {
    components: {
        'Gallery': Gallery // [2] 注册组件
    },
    data() {
        return {
            galleryImageIndex: -1, // Gallery 中当前显示的图片的下标
            images: [ // [3] 准备图片数据，可以后期动态添加
                'https://goss.veer.com/creative/vcg/veer/612/veer-300416794.jpg',
                'https://goss.veer.com/creative/vcg/veer/612/veer-301049438.jpg',
                'https://goss.veer.com/creative/vcg/veer/612/veer-168763769.jpg',
                'https://goss.veer.com/creative/vcg/veer/612/veer-129737258.jpg',
                'https://goss.veer.com/creative/vcg/veer/612/veer-125908355.jpg',
                'https://goss.veer.com/creative/vcg/veer/612/veer-134700985.jpg'
            ]
        };
    }
};
</script>

<style lang="scss">
.images {
    img {
        width: 200px;
        height: 200px;
    }
}
</style>
