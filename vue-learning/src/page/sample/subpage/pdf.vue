<!-- 演示使用 PdfPlayer 在 Tabs 里显示多个 PDF -->
<template>
    <div class="pdf-preview">
        <div class="main">
            <div class="buttons-container">
                <Button>发布</Button>
            </div>

            <!-- Tab 里显示多个 pdf 时，需要去掉动画，否则显示不正常 -->
            <Tabs v-model="tabName" :animated="false" value="name1" class="pdfs">
                <TabPane label="Annots" name="name1">
                    <div v-if="tabName=='name1'" class="pdf">
                        <PdfPlayer :order="'1'" url="/static/temp/annots.pdf"></PdfPlayer>
                    </div>
                </TabPane>
                <TabPane label="Java" name="name2">
                    <div v-if="tabName=='name2'" class="pdf">
                        <PdfPlayer :order="'2'" url="/static/temp/java.pdf"></PdfPlayer>
                    </div>
                </TabPane>
            </Tabs>
        </div>

        <div class="sidebar"></div>
    </div>
</template>

<script>
import PdfPlayer from '@/components/PdfPlayer';

export default {
    data() {
        return {
            tabName: 'name1',
        };
    },
    components: {
        PdfPlayer,
    },
};
</script>

<style lang="scss">
.pdf-preview {
    display: flex;
    flex: 1;

    .sidebar {
        width: 200px;
        background: #808695;
    }

    .main {
        flex: 1;
        display: flex;
        flex-direction: column;
        justify-content: stretch;

        .pdfs {
            flex: 1;
            overflow: hidden;

            .pdf {
                flex: 1;
                display: flex;
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

            .ivu-tabs-content,
            .ivu-tabs-tabpane {
                height: 100%;
            }
        }
    }
}
</style>
