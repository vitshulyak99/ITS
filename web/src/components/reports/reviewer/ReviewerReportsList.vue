<template>
    <div class="d-flex flex-column">

        <span class="m-3">Секция: {{$store.getters.ALL_SECTIONS[0].name}} </span>
        <div class="p-1">
            <div v-for="report in reports" :key="report.id"
                 class="d-flex flex-column p-3 m-1 border border-info border-radius-15">
                <span>Загрузил: <i>{{report.reporter}}</i></span>
                <span>Тема: {{report.name}}</span>
                <review/>
            </div>
        </div>
    </div>
</template>

<script>
    import Review from "./Review";
    export default {
        name: "ReviewerReportsList",
        components: {Review},
        props:['allSections'],

        data(){
            return {
                reports:[
                    {
                        id:0,
                        reporter:'Иван Иванович Иванов',
                        name:'Решение задачи классификации',
                        file:''
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>