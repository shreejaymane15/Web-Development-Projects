// "use server"
// import axios from "axios";
// import { cookies } from "next/headers";

// const baseURL = process.env.NEXT_PUBLIC_BASE_URL;


// const axiosInstance = axios.create(
//     {baseURL,}
// );


// axiosInstance.interceptors.request.use(async (config)=>{
//     debugger;
//     let token = cookies().get('token');
//     if (token) config.headers["Authorization"]= `Bearer ${token}`; 
//     return config;
// },(error) => {
//     return Promise.reject(error);
// });


// export default axiosInstance;