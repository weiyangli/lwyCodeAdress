<template>
    <div class="app">
        <header></header>
        <div class="anchor-test">
            <div class="sidebar">
                <Anchor :affix="true" show-ink container="#content">
                    <template v-for="yearObj in groupedHomeworks">
                        <!-- 年 anchor -->
                        <AnchorLink :href="`#${yearObj.year}`" :title="`${yearObj.year}`" :key="`${yearObj.year}`" />

                        <!-- 月 anchor -->
                        <template v-for="monthObj in yearObj.months">
                            <AnchorLink
                                :href="`#${yearObj.year}-${monthObj.month}`"
                                :title="`${monthObj.month}`"
                                :key="`${yearObj.year}-${monthObj.month}`" />
                        </template>
                    </template>
                </Anchor>
            </div>

            <div id="content">
                <template v-for="yearObj in groupedHomeworks">
                    <!-- 年份 -->
                    <div :id="`${yearObj.year}`" :key="yearObj.year" class="year">{{ yearObj.year }} 年</div>

                    <template v-for="monthObj in yearObj.months">
                        <!-- 月份 -->
                        <div :id="`${yearObj.year}-${monthObj.month}`" :key="`${yearObj.year}-${monthObj.month}`" class="month">
                            <div class="month-label">{{ monthObj.month }} 月</div>

                            <!-- 月下的作业 -->
                            <div v-for="h in monthObj.homeworks" :key="h.id" class="homework">
                                作业内容: {{ h.content }}<br>{{ monthObj.homeworks.length }} 个作业
                            </div>
                        </div>
                    </template>
                </template>
            </div>
            <Button @click="deleteHomework">Delete</Button>
        </div>
    </div>

</template>
<script>

export default {
    data() {
        return {
            homeworks: [
                { id: 1, year: 2016, month: 1, content: 'Month 1-1' },
                { id: 2, year: 2016, month: 1, content: 'Month 1-2' },
                { id: 3, year: 2016, month: 3, content: 'Month 3' },
                { id: 4, year: 2017, month: 6, content: 'Month 6' },
                { id: 5, year: 2017, month: 9, content: 'Month 9' },
                { id: 6, year: 2018, month: 3, content: 'Month 3-1' },
                { id: 7, year: 2018, month: 3, content: 'Month 3-2' },
                { id: 8, year: 2018, month: 5, content: 'Month 5' },
            ]
        };
    },
    computed: {
        groupedHomeworks: function() {
            // 根据年月分组: 先按年分组(key 是年份)，同一年的再按月分组(key 是月份)，同一月的作业放一个数组 homeworks 里，生成的数据结果如下
            /*
            [{
                "year": 2016,
                "months": [{
                    "month": 1,
                    "homeworks": [{ "id": 1, "year": 2016, "month": 1, "content": "Month 1-1" }, { "id": 2, "year": 2016, "month": 1, "content": "Month 1-2" }]
                }, {
                    "month": 3,
                    "homeworks": [{ "id": 3, "year": 2016, "month": 3, "content": "Month 3" }]
                }]
            }, {
                "year": 2017,
                "months": [{
                    "month": 6,
                    "homeworks": [{ "id": 4, "year": 2017, "month": 6, "content": "Month 6" }]
                }]
            }]
            */

            let years = [];

            for (let h of this.homeworks) {
                const yearKey  = h.year;
                const monthKey = h.month;

                // 查找年，如果年不存在则创建
                let yearObj = years.find(y => {
                    return y.year === yearKey;
                });
                if (!yearObj) {
                    yearObj = { year: yearKey, months: [] };
                    years.push(yearObj);
                }

                // 查找年下的月，如果月不存在则创建
                let monthObj = yearObj.months.find(m => {
                    return m.month === monthKey;
                });
                if (!monthObj) {
                    monthObj = { month: monthKey, homeworks: [] };
                    yearObj.months.push(monthObj);
                }

                // 添加月的作业
                monthObj.homeworks.push(h);
            }

            return years;
        }
    },
    methods: {
        deleteHomework: function() {
            this.homeworks.splice(0, 1);
        }
    },
    created() {
        console.log(JSON.stringify(this.groupedHomeworks));
    }
};

</script>
<style lang="scss">
.app {
    display: flex;
    flex-direction: column;

    header {
        height: 60px;
        background: orange;
    }

    .anchor-test {
        display: flex;
        flex: 1;

        .sidebar {
            width: 200px;
            padding-left: 30px;
        }

        #content {
            flex: 1;
            overflow: auto;

            div {
                color: white;
            }

            .year {
                height: 100px;
                background: #5cadff;
            }
            .month {
                .month-label {
                    height: 100px;
                    background: #515a6e;
                }
            }
            .homework {
                height: 200px;
                &:nth-child(even) {
                    background: #19be6b;
                }
                &:nth-child(odd) {
                    background: #17233d;
                }
            }
        }
    }
}
</style>
