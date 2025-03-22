"use client"

import React, {useState} from 'react'
import { useRouter } from 'next/navigation';

export default function Signup() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const router = useRouter();

    const updateUsername = (event) => {
        setUsername(event.target.value)
    }

    const updatePassword = (event) => {
        setPassword(event.target.value)
    }

    const handleSignup = async (event: React.FormEvent) => {
        event.preventDefault();
        // post request json format with username and password
        const signupPostRequest = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                username: username,
                password: password
            })
        }

        try {
            // call backend api and send user json
            const post_response = await fetch('https://day2soap-production.up.railway.app/api/signup', signupPostRequest)
            if (post_response.ok) {
                // if response is received then check if signup was successful (added to database)
                const get_response  = await fetch('https://day2soap-production.up.railway.app/api/signup/status');
                if (await get_response.json()) {
                    router.push('/');
                } else {
                    alert("Signup failed");
                }
            } else {
                alert("Signup failed (fetch worked)");
            }
        } catch (error) {
            // print to console if call fails
            console.log(error)
        }
        
    }

    return (
    <main className='flex justify-center items-center m-[4rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w-md'>
            <h2 className='text-center text-black text-[1.7rem] mb-5'>Sign up</h2>
            <form className='m-auto' onSubmit={handleSignup}>
                <div id='username' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>User Name </label>
                    <input value={username}  onChange={updateUsername} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Enter Username' required>
                    </input>
                </div>

                <div id='password' className='grid'>
                    <label className='text-black'>Password </label>
                    <input value={password} onChange={updatePassword} type="password" className='bg-slate-100 text-black rounded p-2 w-full' placeholder='Enter Password' required>
                    </input>
                </div>

                <div id='signup-button' className='flex justify-center align-middle'>
                    <button className='bg-[#a0eafe] p-3 mt-6 rounded hover:bg-[#c5f2ff] w-full text-white font-bold' type='submit' >
                        Sign up
                    </button>
                </div>

            </form>
        </div>
    </main>
    )
}
