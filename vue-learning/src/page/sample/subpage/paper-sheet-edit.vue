<template lang="html">
    <div class="paper-sheet">
        <h1>答题卡 - {{ correctable ? '批阅模式' : '创建模式' }}</h1>
        <!-- 答题卡试卷的题目 -->
        <QuestionForPaperSheet v-for="question in paper.questions"
            :paper="paper" :question="question" :key="question.id"
            :correctable="correctable" @resultScoreChanged="resultScoreChanged"/>

        <!-- 创建大题的下拉菜单 -->
        <Dropdown v-show="!correctable" class="create-big-question" placement="top" style="margin-top: 20px;" @on-click="appendBigQuestion">
            <Button type="warning" style="width: 150px">创建大题</Button>
            <DropdownMenu slot="list">
                <DropdownItem :name="1">单选题</DropdownItem>
                <DropdownItem :name="2">多选题</DropdownItem>
                <DropdownItem :name="3">判断题</DropdownItem>
                <DropdownItem :name="5">填空题</DropdownItem>
                <DropdownItem :name="4">问答题</DropdownItem>
                <DropdownItem :name="6">复合题</DropdownItem>
            </DropdownMenu>
        </Dropdown>
        <Button v-show="!correctable" type="primary" @click="savePaperSheet">保存试卷 - 共 {{ paper.totalScore }} 分</Button>
    </div>
</template>

<script>
import QuestionForPaperSheet from '@/components/QuestionForPaperSheet';

export default {
    components: {
        QuestionForPaperSheet
    },
    data() {
        return {
            correctable: true, // 是否批阅模式
            paper: {
                id        : 0,
                type      : 1,
                totalScore: 0,
                questions : []
            }
        };
    },
    created() {
        const paperId = this.$route.params.paperId; // 取得 URL 中试卷的 ID

        if (0 != paperId) {
            // 从服务器获取答题卡试卷
            this.$ExamDao.findPaperById(paperId, this.correctable, (paper) => {
                // 赋值题目，渲染界面
                this.paper = paper;
            });
        } else {
            // 创建几个题目演示
            this.appendBigQuestion(1);
            this.appendBigQuestion(2);
            this.appendBigQuestion(3);

            // 测试: 批阅模式时多创建几个题目展示特性
            // if(this.correctable) {
                this.appendBigQuestion(4);
                this.appendBigQuestion(5);
                this.appendBigQuestion(6);

                this.$QuestionUtils.appendSubQuestion(this.paper.questions[this.paper.questions.length-1], 1);
                this.$QuestionUtils.appendSubQuestion(this.paper.questions[this.paper.questions.length-1], 3);
                this.$QuestionUtils.appendSubQuestion(this.paper.questions[this.paper.questions.length-1], 4);
                const fitbQuestion = this.$QuestionUtils.appendSubQuestion(this.paper.questions[this.paper.questions.length-1], 5);

                this.paper.questions[2].status = 1;
                this.paper.questions[3].status = 2;
                this.paper.questions[4].status = 3;
                this.paper.questions[15].status = 2;
                this.paper.questions[28].subQuestions[0].status = 2;
                this.paper.questions[28].subQuestions[1].status = 1;
                this.paper.questions[2].options[0].checked = true;
                this.paper.questions[2].options[2].checked = true;
                this.paper.questions[4].options[1].checked = true;
                this.paper.questions[15].options[1].checked = true;
                this.paper.questions[28].subQuestions[0].options[0].checked = true;
                this.paper.questions[28].subQuestions[0].options[3].checked = true;
                this.paper.questions[28].subQuestions[1].options[0].checked = true;

                this.$QuestionUtils.appendQuestionOption(fitbQuestion);
                this.$QuestionUtils.appendQuestionOption(fitbQuestion);

                this.$QuestionUtils.updateQuestionSnLables(this.paper.questions);
            // }
        }
    },
    methods: {
        // 添加大题, baseType 为大题的题型，同一组题有相同的 groupSn
        appendBigQuestion(baseType) {
            this.$QuestionUtils.appendBigQuestionForPaper(this.paper, baseType);
        },
        // 保存答题卡试卷
        savePaperSheet() {
            // 1. 深拷贝试卷
            // 2. 清理答题卡试卷
            // 3. 发送到服务器保存
            const paper = JSON.parse(JSON.stringify(this.paper));
            this.$QuestionUtils.cleanPaper(paper);

            // [3] 发送到服务器保存 (比较输入的总分和计算得到的试卷总分是否一致)
            this.$ExamDao.updatePaper(paper, (paperId) => {
                alert(paperId);
            });
        },
        resultScoreChanged(question) {
            // 得分变化，把题目的得分附送到服务器保存
            // TODO: console.log(question.result.score);
        }
    }
};
</script>

<style lang="scss">
.paper-sheet {
    padding: 50px;
}

</style>
