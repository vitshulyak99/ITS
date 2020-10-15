<template>
    <div class="p-1">
        <div>
            <div class="form-group">
                <label for="report-title">Тема доклада</label>
                <input type="text"
                       id="report-title"
                       class="form-control"
                       v-model="title">
            </div>

            <div class="form-group">
                <label for="report-section">Секция</label>
                <select v-model="section"
                       ref="reportFiles"
                       id="report-section"
                       class="form-control-file border-radius-5"
                       >
                    <option value='n' selected disabled>Выберите секцию</option>
                    <option v-for="section in $store.getters.SECTIONS"
                            :key="section.id" :value="section.id">{{section.name}}</option>
                </select>
            </div>

            <div class="form-group">
                <label for="report-files">Файлы</label>
                <input type="file"
                       ref="reportFiles"
                       id="report-files"
                       class="form-control-file border-radius-5" multiple>

            </div>
            <button class="btn btn-primary" @click="submit">Загрузить</button>
        </div>
    </div>
</template>

<script>
    import ReportsService from "../../../services/reports/reports-service";

    export default {
        name: "UploadReport",
        data(){
            return {
                title:'',
                section:'n'
            }
        },
        methods:{
            submit(){
                let formData = new FormData();
                this.$refs.reportFiles.files.forEach(file=>{
                    formData.append('files',file);
                });
                formData.append('title',this.title);
                formData.append('sectionId',this.section);
                formData.append('userId',this.$store.getters.ID);

                let reposrtService  = new  ReportsService(this.$store.getters.TOKEN);
                reposrtService.uploadFiles(formData).then(data=>{
                    console.log(data);
                });
            }
        }
    }
</script>

<style scoped>

</style>