<template>
    <div class="d-flex flex-column p-2">
        <select  class="form-control col-5 m-2 " >
            <option value="" v-for="section in allSections" :key="section.id">
                {{section.name}}
            </option>
        </select>
        <button class="btn btn-link btn-primary text-white col-3 m-2">Скачать архивом</button>
    </div>
</template>

<script>
    export default {
        props:['allSections'],
        name: "RedactorDownload"
    }
</script>

<style scoped>

</style>