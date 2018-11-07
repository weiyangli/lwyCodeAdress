<!-- RichTextEx 没有附件，基于 RichText -->
<template>
<div class="bix">
    <!-- 1. 使用 RichText -->
    <RichText ref="editor" :menus="menus" style="display: none" />

    <!-- 2. 每一个可读可编辑的富文本使用 RichTextEx，不使用附件是直接用 v-model 绑定富文本的 HTML -->
    <RichTextEx v-for="(q, index) in questions" v-model="q.html" :key="q.id" closable @close="remove(index)" />

    <Button @click="output">输出数据</Button>
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
            questions: [{
                    id: 1,
                    html: '<p>v-model <font face="Impact">demo</font> Biao</p><p><br></p>'
                },
                {
                    id: 2,
                    html: '<p>Java</p>'
                },
                {
                    id: 3,
                    html: '<p>2018年全新JavaScript技术,针对0基础学员,从O基础到实战的项目讲解-JavaScript公开课BAT大神分享2018全新学习路线,企业面试要求及企业内部项目/案例,点击立即免费听课!</p>'
                }
            ],
            menus: ['fontfamily', 'img', 'mp3', 'file']
        };
    },
    methods: {
        // 删除题目
        remove(index) {
            this.questions.remove(index);
        },
        output() {
            console.log(this.questions);
        }
    }
};
</script>
<style lang="scss">
.bix {
    padding: 50px;

    .richtext-ex {
        border-radius: 4px;
        margin-bottom: 10px;
        width: 700px;
    }
}
</style>
