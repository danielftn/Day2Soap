'use client'
import React, {createContext, useContext, useState, useEffect} from 'react' 
import { useRouter } from 'next/navigation';

export const AuthContext = createContext({
    isLoggedIn: false, 
    login: () => {}, 
    logout: () => {},
});

export const AuthProvider = ({ children })  => {
    const router = useRouter()
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    
    // on loading of the page, save the login status so you dont logout on refreshes
    useEffect(() => {
        const storedLogin = localStorage.getItem("isLoggedIn");
        if (storedLogin === "true") {
            setIsLoggedIn(true);
        }
    }, []);

    // sets login to true and reroutes you to main page
    const login = () => {
        setIsLoggedIn(true);
        localStorage.setItem("isLoggedIn", "true")
        router.push('/main');
    }
    // sets login to false and emptys local storage, then reloads the page so you go back to login page
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

// export login status to other pages
export const useAuth = () => {
    return useContext(AuthContext);
}