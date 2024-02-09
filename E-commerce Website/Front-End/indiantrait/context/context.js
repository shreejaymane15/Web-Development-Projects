"use client";
import { createContext, useContext, useState } from "react";

export const UserContext = createContext();

export default function AppStore({ children }) {
    const [user, setUser] = useState({
        id: 0,
        name: "",
        token: "",
        isAuthenticated: false
    });

  return (
    <UserContext.Provider value={{user, setUser}}>
      {children}
    </UserContext.Provider>
  );
}


