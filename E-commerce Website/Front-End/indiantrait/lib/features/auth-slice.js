import {createSlice, PayloadAction } from "@reduxjs/toolkit";



const initialState = {
    userId: 10,
    token: "",
    isAuthenticated: false,
};


export const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers:{
        logout: (state) =>{
            return initialState;
        },
        login: (state, action) => {
            state.userId = action.payload.userId,
            state.token = action.payload.token,
            state.isAuthenticated = true
        }
    }
});


export const { login, logout }  = authSlice.actions; 

export default authSlice.reducer;

