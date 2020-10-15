<template>
    <div class="d-flex ">
        <div id="mySadBar" class="m-1 navbar-nav list-group sidebar_with d-flex" >
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=0}">Создать пользователя</button>
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=1}">Загрузить сборники</button>
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=2}">Редактировать организационную информацию</button>
        </div>
        <div  class=" ml-2 border-primary col-9 p-3 border border-radius-10">
            <create-user  v-if="focusCaseId===0"/>
            <upload-reports-collection  v-if="focusCaseId===1"/>
            <org-info :canChange="true" v-if="focusCaseId===2"/>
        </div>
    </div>
</template>

<script>
    import CreateUser from "./CreateUser";
    import UploadReportsCollection from "./UploadReportsCollection";
    import OrgInfo from "./OrgInfo";
    export default {
        name: "AdminMain",
        components: {OrgInfo, UploadReportsCollection, CreateUser},
        data(){
            return {
                focusCaseId:0
            }
        }
    }
</script>

<style >


</style>