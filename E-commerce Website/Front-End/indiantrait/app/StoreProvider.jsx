'use client'
import { Provider } from 'react-redux'
import { makeStore, persistor } from '../lib/store'
import { PersistGate } from 'redux-persist/integration/react'

export default function StoreProvider({ children }) {

  return <Provider store={makeStore}>
    <PersistGate persistor={persistor}>
    {children}
    </PersistGate>
    </Provider>
}