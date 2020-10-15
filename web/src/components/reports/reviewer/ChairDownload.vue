<template>
    <div class="d-flex flex-column p-2">
<!--        <select  class="form-control col-5 m-2 " >-->
<!--            <option value="" v-for="section in allSections" :key="section.id">-->
<!--                {{section.name}}-->
<!--            </option>-->
<!--        </select> -->
        <span class="mt-2">Секция: {{allSections[0].name}}</span>
        <button class="btn btn-link btn-primary text-white col-3 m-2">Скачать архивом</button>
    </div>
</template>

<script>
    export default {
        name: "ChairDownload",
        props:['allSections'],
        data(){
            return{
                sectionId:''
            }
        }
    }
</script>

<style scoped>

</style>