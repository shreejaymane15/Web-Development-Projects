import { configureStore } from '@reduxjs/toolkit'
import authReducer from '@/lib/features/auth-slice'
import {persistStore, persistReducer, FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

const persistConfig = {
  key:"root",
  version: 1,
  storage,
}

const persistedReducer = persistReducer(persistConfig, authReducer)

export const makeStore = configureStore({
    // reducer:{
    //   auth: authReducer,
    // }
    reducer: persistedReducer,
    middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck:{
        ignoreActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
})


export let persistor = persistStore(makeStore);
