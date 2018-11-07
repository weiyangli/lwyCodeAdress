<!-- RichText 基本使用 -->
<template>
    <div style="padding: 30px">
        <!-- <RichText ref="editor"/> -->
        <RichText ref="editor" :html="html" :attachments="attachments"/>
        <Button @click="getEditContent">获取编辑器内容</Button>
    </div>
</template>
<script>
    import RichText from '@/components/RichText';

    export default {
        components: {
            RichText
        },
        data() {
            return {
                html: '<p>Hello</p>',
                attachments: [{ id: 1234, filename: 'Jobs.png', url: '/file/data/2018-05-03/165694386577866752.png' }]
            };
        },
        mounted() {

        },
        methods: {
            fileUploaded() {

            },
            getEditContent(e) {
                // 获取富文本编辑器的内容，包含 html 和附件 attachments
                let richText = this.$refs.editor.content;
                console.log(richText);

                // 提交给服务器，因为富文本的内容是复杂对象，所以数据使用 request body 的方式提交
                $.rest.create({ url: '/api/demo/richText',
                    jsonRequestBody: true,
                    data: richText,
                    success: (result) => {
                        console.log(result);
                    }
                });
            }
        }
    };
</script>
