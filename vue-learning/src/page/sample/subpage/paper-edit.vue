<!-- 创建试卷的例子 -->
<template lang="html">
    <div class="create-paper">
        <RichText ref="editor" :menus="menus" style="display: none" />

        <!-- 增加小题的下拉菜单 -->
        <Dropdown size="small" style="width: 100px;" @on-click="appendQuestion">
            <a href="javascript:void(0)"><Icon type="md-add"></Icon> 增加题目</a>
            <DropdownMenu slot="list">
                <DropdownItem :name="1">单选题</DropdownItem>
                <DropdownItem :name="2">多选题</DropdownItem>
                <DropdownItem :name="3">判断题</DropdownItem>
            </DropdownMenu>
        </Dropdown>

        <div class="questions">
            <template v-for="(question, index) in paper.questions">
                <!-- 从题库选的题目: 不可编辑 -->
                <!-- 老师创建的课堂评测题目: 可编辑 -->
                <Question
                    :question="question" :key="question.id"
                    :read-only="0 === question.purpose"
                    answer-invisible analysis-invisible closable bordered correctable correct-buttons-visible
                    @close="deleteQuestion(index)" @questionStatusChanged="questionStatusChanged" />
            </template>
        </div>

        <Button type="primary" style="margin-top: 10px;" @click="savePaper">提交</Button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            paper: {
                id: 0,
                questions: [],
            },
            menus: ['head', 'bold', 'underline', 'orderlist', 'unorderlist', 'fontfamily', 'img', 'mp3', 'fitb'],
        };
    },
    mounted() {
        const paperId = this.$route.params.paperId; // 取得 URL 中试卷的 ID

        if (0 != paperId) {
            this.$ExamDao.findPaperById(paperId, (paper) => {
                // 更新题目前面的 A, B, C, D
                for (let i = 0; i < paper.questions.length; ++i) {
                    this.$QuestionUtils.updateOptionMarks(paper.questions[i]);
                }

                this.paper = paper;

                // 生成题目的序号
                this.$QuestionUtils.updateQuestionSnLables(this.paper.questions);
            });
        } else {
            // 创建试卷时先添加几个题目，方便测试
            // 单选题
            const question1 = this.$QuestionUtils.createQuestion(Utils.nextId(), 1);
            question1.purpose = 3; // 老师创建的题
            // question1.deleted = true;
            question1.options[1].checked = true;
            this.paper.questions.push(question1);

            // 单选题 (题库中, purpose 为 0)
            const question2 = this.$QuestionUtils.createQuestion(Utils.nextId(), 1, '<p>从题目选择的题目</p>', 0);
            question2.options[0].content = '1';
            question2.options[1].content = '2';
            question2.options[2].content = '3';
            question2.options[3].content = '4';
            question2.options[0].checked = true;
            question2.options[2].checked = true;
            this.paper.questions.push(question2);

            // 作业题 (需要确认)
            const question3 = this.$QuestionUtils.createQuestion(Utils.nextId(), 7, '作业题', 2);
            this.paper.questions.push(question3);

            // 问答题
            const question4 = this.$QuestionUtils.createQuestion(Utils.nextId(), 4, '问答题', 0);
            question4.status = 3;
            this.paper.questions.push(question4);

            // 复合题
            const question5 = this.$QuestionUtils.createQuestion(Utils.nextId(), 6, '复合题', 0);
            this.$QuestionUtils.appendSubQuestion(question5, 1);
            this.$QuestionUtils.appendSubQuestion(question5, 3);
            this.$QuestionUtils.appendSubQuestion(question5, 4);
            this.$QuestionUtils.appendSubQuestion(question5, 5);
            question5.subQuestions[0].options[0].checked = true;
            question5.subQuestions[0].options[3].checked = true;
            question5.subQuestions[1].options[1].checked = true;
            this.paper.questions.push(question5);

            // 判断题
            const question6 = this.$QuestionUtils.createQuestion(Utils.nextId(), 3);
            question6.purpose = 3; // 老师创建的题
            question6.options[0].checked = true;
            this.paper.questions.push(question6);

            // 生成题目的序号
            this.$QuestionUtils.updateQuestionSnLables(this.paper.questions);
        }
    },
    methods: {
        // 添加题目
        appendQuestion(baseType) {
            const question = this.$QuestionUtils.createQuestion(Utils.nextId(), baseType);
            question.purpose = 3;
            this.paper.questions.push(question);
            this.$QuestionUtils.updateQuestionSnLables(this.paper.questions);
        },
        // 删除问题
        deleteQuestion(index) {
            this.paper.questions[index].deleted = true; // 标记为已删除
            this.$QuestionUtils.updateQuestionSnLables(this.paper.questions);
        },
        questionStatusChanged(question) {
            // alert(`Question ID: ${question.id}, Status: ${question.status}`);
        },
        savePaper() {
            // 保存试卷逻辑:
            // 1. 深拷贝试卷，为了下面的操作不影响界面上的试卷内容
            // 2. 清理试卷
            // 3. 校验试卷, 试卷有效提交给服务器

            // [1] 深拷贝试卷，为了下面的操作不影响界面上的试卷内容
            let paper = JSON.parse(JSON.stringify(this.paper));
            // [2] 清理试卷
            this.$QuestionUtils.cleanPaper(paper);

            // [3] 试卷有效提交给服务器
            if (this.$QuestionUtils.validatePaper(paper)) {
                // 保存到服务器
                this.$ExamDao.updatePaper(paper, (paperId) => {
                    this.paper.id = paperId;
                });
            }
        }
    }
};
</script>

<style lang="scss">

.question {
    margin-bottom: 10px;
}
</style>
