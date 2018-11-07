<!-- 演示使用 PdfPlayer 在 Tabs 里显示多个 PDF -->
<template>
    <div class="pdf-preview">
        <header></header>

        <Split v-model="split1">
            <div slot="left" class="pdf-split-pane">
                <Card :bordered="false" dis-hover>
                    <p slot="title">Java 编程规范</p>

                    <!-- Tab 里显示多个 pdf 时，需要去掉动画，否则显示不正常 -->
                    <Tabs :animated="false" value="name1" class="pdfs">
                        <TabPane label="Annots" name="name1"><div v-if="loading" class="pdf"><PdfPlayer :order="'1'" url="/static/temp/annots.pdf"></PdfPlayer></div></TabPane>
                        <TabPane label="Java" name="name2"><div v-if="loading" class="pdf"><PdfPlayer :order="'2'" url="/static/temp/java.pdf"></PdfPlayer></div></TabPane>
                    </Tabs>
                </Card>
            </div>

            <div slot="right" class="paper-split-pane">
                <Card :bordered="false" dis-hover>
                    <p slot="title">答题卡试卷</p>
                    <a slot="extra" href="#" @click.prevent="changeLimit">
                        <Icon type="md-bicycle" /> Change
                    </a>
                    <div class="paper-sheet">
                        <div class="question"></div>
                        <div class="question"></div>
                        <div class="question"></div>
                        <div class="question"></div>
                        <div class="question"></div>
                    </div>
                </Card>
            </div>
        </Split>
    </div>
</template>

<script>
import PdfPlayer from '@/components/PdfPlayer';

export default {
    components: {
        PdfPlayer
    },
    data() {
        return {
            split1: 0.75,
            loading: false
        };
    },
    mounted() {
        this.$nextTick(() => {
            this.loading = true; // 显示 PDF 的元素大小准备好后再显示 PDF，否则 PDF 的大小计算不正确
        });
    }
};
</script>

<style lang="scss">
.pdf-preview {
    display: flex;
    flex-direction: column;
    flex: 1;

    header {
        height: 60px;
        background: #808695;
    }

    /* 有多个层次，每一层都需要设置高度，把高度从最上一层传递到最下一层 */
    .pdf-split-pane, .paper-split-pane {
        display: flex;
        height: 100%;

        .ivu-card {
            display: flex;
            flex-direction: column;
            flex: 1;

            .ivu-card-body {
                display: flex;
                flex-direction: column;
                flex: 1;
                overflow: auto;

                .pdfs {
                    flex: 1;

                    .pdf {
                        display: flex;
                        flex: 1;
                        height: 100%;
                        overflow: auto;
                        position: relative;

                        .pdf-player {
                            width: 100%;
                        }
                    }

                    .ivu-tabs-bar {
                        margin-bottom: 0;
                    }

                    .ivu-tabs-content, .ivu-tabs-tabpane {
                        height: 100%;
                    }
                }
            }
        }
    }

    .paper-split-pane {
        .paper-sheet {
            .question {
                height: 300px;
                &:nth-child(odd) {
                    background: #5cadff;
                }
                &:nth-child(even) {
                    background: #19be6b;
                }
            }
        }
    }
}

</style>
