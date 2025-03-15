'use client'
import React, {createContext, useContext, useEffect, useState} from 'react' 

export const UserContext = createContext<{
    user: string | null;
    switchUser: (user: string) => void;
  } | null>(null);

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState<string | null>(null);

    const switchUser = (u: string) => {
        setUser(u);
    }

    return (
        <UserContext.Provider value={{ user, switchUser }}>
            { children }
        </UserContext.Provider>
    );
};

export const useUser = () => {
    const c =  useContext(UserContext);
    if (!c) {
        throw new Error("error")
    }
    return c;
}