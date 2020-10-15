<template>
    <div class="container d-flex">
        <div id="mySadBar" class="m-1 navbar-nav list-group sidebar_with d-flex" >
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=0}">Изменить пароль</button>
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=1}">Изменить ФИО</button>
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=2}">Изменить контактные данные</button>

        </div>
        <div  class=" ml-2 border-primary col-9 p-3 border border-radius-10">

            <change-password  v-if="focusCaseId===0"/>
            <change-name  v-if="focusCaseId===1"/>
            <change-contact v-if="focusCaseId===2"/>
        </div>


    </div>
</template>

<script>


    import ChangeName from "./ChangeName";
    import ChangePassword from "./ChangePassword";
    import ChangeContact from "./ChangeContact";

    export default {
        name: "manage-info",
        components: {ChangeContact, ChangeName, ChangePassword},
        created(){
            if(this.$store.getters.USER===null){
                this.$router.push({ name: 'home' })
            }
        },
        data(){
            return {
                focusCaseId:0
            }
        }
    }

</script>

