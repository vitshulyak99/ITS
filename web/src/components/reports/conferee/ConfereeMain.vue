<template>
    <div class="d-flex ">
        <div id="mySadBar" class="m-1 navbar-nav list-group sidebar_with d-flex" >
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=0}">Список докладов</button>
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=1}">Загрузить доклад</button>
        </div>
        <div  class=" ml-2 border-primary col-9 border-radius-10 border p-3">
           <report-list v-if="focusCaseId===0"/>
           <upload-report v-if="focusCaseId===1"/>
        </div>
    </div>
</template>

<script>
    import ReportList from "./ReportList";
    import UploadReport from "./UploadReport";
    export default {
        name: "ConfereeMain",
        components: {UploadReport, ReportList},
        data(){
            return {
                focusCaseId:1
            }
        }
    }
</script>

<style>

    .sidebar_with{
        width: 150px;
        font-size: 10px;
    }

</style>