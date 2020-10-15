<template>
    <div class="d-flex flex-column p-2">

        <select  class="form-control col-5 m-2 " >
            <option value="" v-for="section in allSections" :key="section.id">
                {{section.name}}
            </option>
        </select>
        <div class="p-1">
            <div v-for="report in reports" :key="report.id"
                 class="d-flex flex-column p-3 m-1 border border-info border-radius-15">
                <span>Загрузил: <i>{{report.reporter}}</i></span>
                <span>Тема: {{report.name}}</span>
                <redactor-approve/>
            </div>
        </div>
    </div>
</template>

<script>

    import RedactorApprove from "./RedactorApprove";
    export default {
        props:['reports','allSections'],
        name: "RedactorReportsList",
        components: {RedactorApprove},
    }
</script>

<style scoped>

</style>