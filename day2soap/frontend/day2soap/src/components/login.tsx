"use client"

import React, {useState} from 'react'
import {useAuth} from '../context/AuthContext'
import {useUser} from '../context/UserContext'
import Link from 'next/link'

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { login } = useAuth();
    const { switchUser } = useUser();
    
    const updateUsername = (event) => {
        setUsername(event.target.value)
    }

    const updatePassword = (event) => {
        setPassword(event.target.value)
    }

    const handleLogin = async (event) => {
        event.preventDefault();
        // post request json format with username and password
        const loginPostRequest = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                username: username.trim(),
                password: password
            })
        }

        try {
            // call backend api and send user json
            const post_response = await fetch('https://day2soap-production.up.railway.app/api/login', loginPostRequest)
            // if response is received then check which user is logged in by calling another api
            if (post_response.ok) {
                const get_response = await fetch('https://day2soap-production.up.railway.app/api/login/status');
                // save the user information returned
                const data = await get_response.json();
                // check if the username returned is not empty
                if (data.username !== null) {
                    login();
                    switchUser(data.username);
                } else {
                    alert('Login failed');
                }
            } else {
                alert('Login failed (post failed)');
            }
        } catch (error) {
            // print to console if call fails
            console.log(error)
        }
        
    }
  return (
    <main className='flex justify-center items-center m-[4rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w-md'>
            <h2 className='text-center text-black text-[1.7rem] mb-5'>Login</h2>
            <form className='m-auto' onSubmit={handleLogin}>
                <div id='username' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>User Name </label>
                    <input value={username}  onChange={updateUsername} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Enter Username' required>
                    </input>
                </div>

                <div id='username' className='grid'>
                    <label className='text-black'>Password </label>
                    <input value={password} onChange={updatePassword} type="password" className='bg-slate-100 text-black rounded p-2 w-full' placeholder='Enter Password' required>
                    </input>
                </div>

                <div id='button' className='flex justify-center align-middle'>
                    <button className='bg-[#a0eafe] p-3 mt-6 rounded hover:bg-[#c5f2ff] w-full text-white font-bold' type='submit'>
                        Login
                    </button>
                </div>

                <div id='guest-selection'>
                    <p className='flex justify-center text-gray-400 mt-3'>
                        <Link href='/main' className='hover:text-gray-300'>Continue as Guest</Link>
                    </p>
                </div>

                <hr className='m-4'></hr>

                <div className='flex justify-center '>
                    <p className='flex text-gray-400 mr-2'>
                        Don&apos;t have an account? 
                    </p>
                    <Link href='/signup' className='text-blue-500 hover:text-blue-200'>Sign up now</Link>
                </div>

            </form>
        </div>
    </main>
  )
}
