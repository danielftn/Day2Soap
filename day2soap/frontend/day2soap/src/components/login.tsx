"use client"

import React from 'react'
import {useState} from 'react'
import Link from 'next/link'

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const updateUsername = (event) => {
        setUsername(event.target.value)
    }

    const updatePassword = (event) => {
        setPassword(event.target.value)
    }

    const handleLogin = async () => {
        const loginPostRequest = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                username: username,
                password: password
            })
        }

        try {
            const post_response = await fetch('http://localhost:8080/api/login', loginPostRequest)
            if (post_response.ok) {
                const get_response = await fetch('http://localhost:8080/api/login/status');
                if (await get_response.json()) {
                    alert('Login success!');
                } else {
                    alert('Login failed');
                }
            } else {
                alert('Login failed (post failed)');
            }
        } catch (error) {
            console.log(error)
        }
        
    }
  return (
    <main className='flex justify-center align-middle items-center m-[8rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w-md'>
            <h2 className='text-center text-black text-[1.7rem] mb-5'>Login</h2>
            <form className='m-auto'>
                <div id='username' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>User Name </label>
                    <input value={username}  onChange={updateUsername} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Enter Username' required>
                    </input>
                </div>

                <div id='username' className='grid'>
                    <label className='text-black'>Password </label>
                    <input value={password} onChange={updatePassword} type="password" className='bg-slate-100 text-black rounded p-2 ' placeholder='Enter Password' required>
                    </input>
                </div>

                <div id='button' className='flex justify-center align-middle'>
                    <button onClick={handleLogin} className='bg-[#a0eafe] p-3 mt-6 rounded hover:bg-[#c5f2ff] w-full'>
                        <Link href='/' className='text-white'>Login</Link>
                    </button>
                </div>

                <div id='guest-selection'>
                    <p className='flex justify-center text-gray-400 mt-3'>
                        <Link href='' className='hover:text-gray-300'>Continue as Guest</Link>
                    </p>
                </div>

                <hr className='m-4'></hr>

                <div className='flex justify-center '>
                    <p className='flex text-gray-400 mr-2'>
                        Don't have an account? 
                    </p>
                    <Link href='/signup' className='text-blue-500 hover:text-blue-200'>Sign up now</Link>
                </div>

            </form>
        </div>
    </main>
  )
}
