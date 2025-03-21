"use client";
import React, { useEffect, useState } from "react";
import { useUser } from "@/context/UserContext";
import { Movie } from "@/models/Movie";

export default function History() {
  const { user } = useUser();
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
    if (!user) return;

    const fetchHistory = async () => {
      try {
        const response = await fetch(`https://day2soap-production.up.railway.app/api/history?username=${user}`);
        if (response.ok) {
          const data: Movie[] = await response.json();
          setMovies(data);
        }
      } catch (error) {
        console.error("Error fetching history:", error);
      }
    };

    fetchHistory();
  }, [user]);

  return (
    <main className="flex justify-center items-center m-[2rem] sm:m-[4rem]">
      <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w sm:mx-10 mx-2">
        <h2 className="text-left text-black text-[1.7rem] mb-5">History</h2>
        
        {movies.length === 0 ? (
          <p className="text-gray-500">No movies saved yet.</p>
        ) : (
          <ul className="space-y-4">
            {movies.map((movie, index) => (
              <li key={index} className="border p-4">
                <h3 className="text-xl font-semibold text-black">{movie.title} ({movie.year})</h3>
                <p className="text-gray-600">{movie.description}</p>
              </li>
            ))}
          </ul>
        )}
      </div>
    </main>
  );
}
