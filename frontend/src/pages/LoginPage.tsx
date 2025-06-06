import { useState, type FormEvent } from 'react';
import { useAuth } from '../auth';

export const LoginPage = () => {
  const { login } = useAuth();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [code, setCode] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError('');
    const res = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password, code }),
    });
    if (res.ok) {
      const data: { token: string } = await res.json();
      login(data.token);
    } else {
      setError('Invalid credentials');
    }
  };

  return (
    <div className="flex items-center justify-center h-screen">
      <form onSubmit={handleSubmit} className="space-y-2 p-4 border rounded">
        <input
          className="border p-1 block w-full"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          className="border p-1 block w-full"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input
          className="border p-1 block w-full"
          placeholder="TOTP Code"
          value={code}
          onChange={(e) => setCode(e.target.value)}
        />
        {error && <div className="text-red-600">{error}</div>}
        <button type="submit" className="bg-blue-500 text-white px-2 py-1">
          Login
        </button>
      </form>
    </div>
  );
};
