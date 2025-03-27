import { createContext, useContext, useState } from 'react';

const UserSessionContext = createContext();

export function UserSessionProvider({ children }) {
  const [userSession, setUserSession] = useState(null);

  return (
    <UserSessionContext.Provider value={{ userSession, setUserSession }}>
      {children}
    </UserSessionContext.Provider>
  );
}

export function useUserSession() {
  return useContext(UserSessionContext);
}