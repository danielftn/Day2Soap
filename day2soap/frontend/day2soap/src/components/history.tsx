"use client"
import { useUser } from '@/context/UserContext'
import { useEffect, useState } from "react";

export default function History() {
  const { user } = useUser();
  const [movieHistory, setMovieHistory] = useState([]);

  const handleHistory = async () => {
    const historyPostRequest = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            username: user,
            password: null
        })
    }

    try {
        const post_response = await fetch('http://localhost:8080/api/history', historyPostRequest)
        if (post_response.ok) {
            const get_response = await fetch('http://localhost:8080/api/history/status');
            const data = await get_response.json();
            console.log(data);
            setMovieHistory(data);
        } else {
            alert('history retrieval failed (post failed)');
        }
    } catch (error) {
        console.log(error)
    }
    
  }

  useEffect(() => {
    console.log(user);
    handleHistory();

  }, []);

  return (
    <main className='flex justify-center align-middle items-center m-[8rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w mx-10'>
            <h2 className='text-left text-black text-[1.7rem] mb-5'>History</h2>
            <div>
              {movieHistory.map((movie, index) => (
                  <div key={index} className='text-black mb-5'>
                      <h2>{movie.title} - {movie.year}</h2> 
                      <p>{movie.description}</p>
                  </div>
              ))}
            </div>
        </div>
    </main>
  )
}
