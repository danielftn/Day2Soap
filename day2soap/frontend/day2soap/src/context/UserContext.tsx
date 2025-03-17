'use client'
import React, {createContext, useContext, useEffect, useState} from 'react' 

export const UserContext = createContext<{
    user: string | null;
    switchUser: (user: string) => void;
  } | null>(null);

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState<string | null>(null);

    useEffect(() => {
        const storedUser = localStorage.getItem("user");
        if (storedUser) {
            setUser(storedUser);
        }   
    }, []);

    const switchUser = (u: string) => {
        setUser(u);
        localStorage.setItem("user", u);
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