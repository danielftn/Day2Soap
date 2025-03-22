'use client'
import React, {createContext, useContext, useEffect, useState} from 'react' 

export const UserContext = createContext<{
    user: string | null;
    switchUser: (user: string) => void;
  } | null>(null);

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState<string | null>(null);

    // on loading of the page, save the user information so you dont lose track of whos logged in on refreshes
    useEffect(() => {
        const storedUser = localStorage.getItem("user");
        if (storedUser) {
            setUser(storedUser);
        }   
    }, []);

    // changes which user is logged in
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

// exports user information to other pages
export const useUser = () => {
    const c =  useContext(UserContext);
    if (!c) {
        throw new Error("error")
    }
    return c;
}