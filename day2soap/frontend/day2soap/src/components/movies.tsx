"use client";
import React, { useState, useEffect } from "react";
import { Movie } from "@/models/Movie";
import { useUser } from "@/context/UserContext";

export default function Movies() {
  const { user } = useUser();
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
      if (!user) return;
  
      const fetchHistory = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/history?username=${user}`);
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
  

  // Places checkboxed movies at the top of the list
  const handleCheckboxChange = (title, checked) => {
    console.log(`Movie: ${title}, Checked: ${checked}`);
    setMovies((prevMovies) =>
      prevMovies.map((movie) =>
        movie.title === title ? { ...movie, watched: checked } : movie
      )
    );
    const updatedMovie = movies.find((movie) => movie.title === title);
    if(updatedMovie){
      updatedMovie.watched = checked;
    }
    updateMovie(updatedMovie);
  };

  const updateMovie = async (updatedMovie) => {
    console.log("Updating movie: ", updatedMovie);
    try{
      const response = await fetch(`http://localhost:8080/api/watched`, { 
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username: user, movie: updatedMovie})
      });
      if(response.ok){
        console.log("Movie updated successfully")
      }
    } catch (error) {
      console.log("Error updating movie: " , error)
    }
  };

  // Sorts the Movies in Alphabetical order
  const sortedMovies = [...movies].sort(
    (a, b) => Number(a.watched)- Number(b.watched) 
  );

  return (
    <main className="flex justify-center align-middle items-center m-[8rem]">
      <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w mx-10">
        <h2 className="text-left text-black text-[1.7rem] mb-5">Movies</h2>
        <div
          id="movies-box"
          className=" flex justify-center bg-slate-100 p-6 rounded-lg shadow-lg w-9/10"
        >
          {movies.length === 0 ? (
            <p className="text-gray-500 text-left w-full"> No movies saved yet.</p>
          ) : (
          <ul className="flex flex-col justify-start w-full" id="movies-list">
            {sortedMovies.map((movie) => (
              <li
                key={movie.title}
                className="text-left text-black flex items-center mb-1"
              >
                <input type="checkbox" className="mr-2 w-4 h-4" checked={movie.watched} onChange={(e) => handleCheckboxChange(movie.title, e.target.checked)} />
                <label className="font-bold">{movie.title} ({movie.year})</label>
              </li>
            ))}
          </ul>
          )}
        </div>
      </div>
    </main>
  );
}
