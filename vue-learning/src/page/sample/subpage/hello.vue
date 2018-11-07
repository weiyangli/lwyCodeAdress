<template>
    <div class="teacher-space">
        <TeacherHeader/>
    </div>
</template>

<script>
    export default {
        data() {
            return {};
        },
        methods: {
            selectFinish(provinceId, cityId, countyId) {
                console.log(provinceId, cityId, countyId);
            }
        }
    };
</script>

<style>

</style>
