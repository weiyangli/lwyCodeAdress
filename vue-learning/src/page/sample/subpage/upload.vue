<template>
    <div class="upload-demo">
        <FileUploader ref="fileUploader" image @on-file-uploaded="fileUploaded" />
        <Button @click="showUploadDialog">点击上传文件</Button>
    </div>
</template>

<script>
    import FileUploader from '@/components/FileUploader';

    export default {
        components: {
            FileUploader
        },
        data() {
            return {};
        },
        methods: {
            showUploadDialog() {
                this.$refs.fileUploader.show();
            },
            fileUploaded(files) {
                console.log(JSON.stringify(files));
            }
        }
    };
</script>

<style>
    .upload-demo {
        padding: 100px;
    }
</style>
