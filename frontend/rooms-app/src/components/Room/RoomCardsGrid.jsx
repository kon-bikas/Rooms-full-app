import React from 'react';
import RoomCard from '../Profile/RoomCard';
import { v4 as uuidv4 } from 'uuid';

const RoomCardsGrid = ({ children, cards }) => {

      return(

            <div className = "room-cards-grid">

                  { cards?.map((cur_card) => 
                        <RoomCard 
                              key = { uuidv4() } 
                              card = { cur_card }
                        />
                  ) }

                  { children }

            </div>

      );

}

export default RoomCardsGrid;