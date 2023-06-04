import { useState } from 'react'

export function Accordion({ text, header }: { text: string; header: string }) {
  const [isActive, setIsActive] = useState(false)

  return (
    <>
      <li>
        <h4 className="client_h" onClick={() => setIsActive(!isActive)}>
          {header}
        </h4>
        {isActive && <p className="text">{text}</p>}
      </li>
    </>
  )
}
