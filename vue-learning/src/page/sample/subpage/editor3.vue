<!-- RichTextEx 有附件，基于 RichText -->
<template>
<div class="Bix">
    <!-- 1. 使用 RichText -->
    <RichText ref="editor" :menus="menus" style="display: none" />

    <!-- 2. 每一个可读可编辑的富文本使用 RichTextEx，使用附件时需要用 :html 和 :attachments 进行初始化，并监听编辑结束事件 -->
    <RichTextEx
        v-for="(q, index) in questions"
        :html="q.html"
        :attachments="q.attachments"
        :key="q.id" closable
        @close="remove(index)" @editing-finished="editingFinished(index, $event)" />

    <Button @click="remove(0)">删除第一个题目</Button>
</div>
</template>

<script>
import RichText from '@/components/RichText';
import RichTextEx from '@/components/RichTextEx';

export default {
    components: {
        RichText,
        RichTextEx
    },
    data() {
        return {
            question: {
                html: '<p>v-model <font face="Impact">demo</font> Biao</p><p><br></p>'
            },
            questions: [{
                    id: 1,
                    html: '<p>PHP</p>',
                    attachments: [{
                        id: 1231,
                        filename: 'Php.png',
                        url: '/file/data/2018-05-03/165694386577866752.png'
                    }]
                },
                {
                    id: 2,
                    html: '<p>Java</p>',
                    attachments: [{
                        id: 1232,
                        filename: 'Java.png',
                        url: '/file/data/2018-05-03/165694386577866752.png'
                    }]
                },
                {
                    id: 3,
                    html: '<p>2018年全新JavaScript技术,针对0基础学员,从O基础到实战的项目讲解-JavaScript公开课BAT大神分享2018全新学习路线,企业面试要求及企业内部项目/案例,点击立即免费听课!</p>',
                    attachments: [{
                        id: 1233,
                        filename: 'JavaScript 就是这么长的.jpg',
                        url: '/file/data/2018-05-03/165694386577866752.png'
                    }, {
                        id: 1234,
                        filename: 'Fox.pdf',
                        url: '/file/data/2018-05-03/165694386577866752.pdf'
                    }]
                }
            ],
            menus: ['fontfamily', 'img', 'mp3', 'upload']
        };
    },
    methods: {
        // 删除题目
        remove(index) {
            this.questions.remove(index);
        },
        // 编辑结束的时候设置题目的内容
        editingFinished(index, event) {
            const q = this.questions[index];
            q.html = event.html;
            q.attachments = event.attachments;
            console.log(q);
        }
    }
};
</script>
<style media="screen">
.richtext-ex {
    border-radius: 4px;
    margin-bottom: 10px;
}
</style>
