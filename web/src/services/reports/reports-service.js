
import Axios from "axios";

import ReportEndpoints from './report-endpoints'
import AuthorizedService from "../authorized-service";



export  default class  ReportsService extends  AuthorizedService{

    constructor(token) {
        super(token);
    }

    uploadFiles(formData){
        return  Axios.post(ReportEndpoints.ConfereeReportsUpload,formData,
            {
                headers:{
                    'Content-Type': 'multipart/form-data',
                    Authorization: this.token
                }
            })
            .then(response => {

                console.log(response.data);

                return response.data;
            })
            .catch(e=> {
                console.log(e);
            throw e;
            })
    }

    getSections(){
        console.log(this.token);
        return this.createGet(ReportEndpoints.GetSections).then(response =>{

            console.log(response);
            return response.data;
        })
            .catch(e=> {
                throw e;
            });
    }

    choiceSections(userId,sectionIds){
        console.log(this.token);

        return this.createPost(ReportEndpoints.ChoiceSections,{
            userId,
            sectionIds
        }).then(response => response.data)
            .catch(e=> {
                throw e;
            });
    }

    getAllReportsForUser(formData) {
        return this.createGet(ReportEndpoints.GetReports,formData)
            .then(response => response.data)
            .catch(e=> {
                throw e;
            });
    }

    getAllReportsFromSection(formData){
        return this.createPost(ReportEndpoints.GetReportsReviewer,
            formData)
            .then(response=>{
                return response.data;
            });
    }


}
