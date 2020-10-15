<template>
    <div class="d-flex ">
        <div id="mySadBar" class="m-1 navbar-nav list-group sidebar_with d-flex" >
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=0}">Список докладов</button>
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=1}">Скачать архивом</button>

        </div>
        <div  class=" ml-2 border-primary col-9 border-radius-10 border p-3">
            <redactor-reports-list :all-sections="allSections" :reports="reports" v-if="focusCaseId===0"/>
            <redactor-download :allSections="allSections" v-if="focusCaseId===1"/>
        </div>
    </div>
</template>

<script>
    import RedactorReportsList from "./RedactorReportsList";

    import RedactorDownload from "./RedactorDownload";
    export default {
        name: "RedactorMain",
        components: {RedactorDownload,  RedactorReportsList},
        data(){
            return {
                focusCaseId:0,
                reports:[
                    {
                        id:0,
                        reporter:'Иван Иванович Иванов',
                        name:'Решение задачи классификации с помощью сверточных нейронных сетей',
                        file:''
                    }
                ]
            }
        },
        computed:{
            isChair(){
                return this.$store.getters.ROLES.some(r=>r.name==='CHAIR');
            },
            allSections(){
                return this.$store.getters.ALL_SECTIONS;
            }
        }
    }
</script>

<style scoped>

</style>