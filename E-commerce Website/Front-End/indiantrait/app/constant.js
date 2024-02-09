export let baseUrl = "http://localhost:8060/indiantrait";



export default function createUrl(uri){
    var url = "http://localhost:8060/indiantrait"
    url = url.concat(uri);
    return url;
}


