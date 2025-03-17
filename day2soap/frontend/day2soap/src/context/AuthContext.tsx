'use client'
import React, {createContext, useContext, useState, ReactNode, useEffect} from 'react' 
import { useRouter } from 'next/navigation';

export const AuthContext = createContext({
    isLoggedIn: false, 
    login: () => {}, 
    logout: () => {},
});

export const AuthProvider = ({ children })  => {
    const router = useRouter()
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    
    useEffect(() => {
        const storedLogin = localStorage.getItem("isLoggedIn");
        if (storedLogin === "true") {
            setIsLoggedIn(true);
        }
    }, []);

    const login = () => {
        setIsLoggedIn(true);
        localStorage.setItem("isLoggedIn", "true")
        router.push('/main');
    }
    const logout = () => {
        setIsLoggedIn(false);
        localStorage.removeItem("isLoggedIn");
        localStorage.removeItem("user");  
        router.push('/');
        window.location.reload();  
    };
    
    return (
        <AuthContext.Provider value={{ isLoggedIn, login, logout}}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => {
    return useContext(AuthContext);
}