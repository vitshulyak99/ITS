import Axios from "axios";
import PublicDataEndpoints from './public-data-endpoints';

class PublicDataService {
    static getSections(){
        return Axios.get(PublicDataEndpoints.Sections)
            .then(response=>{
                return response.data;
            })
            .catch(e=>{
                console.log(e);
            })
    }

    static getDegrees(){
        return Axios.get(PublicDataEndpoints.Degrees)
            .then(response=>{
                return response.data
            })
            .catch(e=>{
                console.log(e);
            })
    }

    static getRoles(){
        return Axios.get(PublicDataEndpoints.Roles)
            .then(response=>{
                return response.data;
            })
            .catch(e=>{
                console.log(e);
            })
    }
}

export default PublicDataService;