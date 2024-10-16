import React from 'react';

const DeleteRecipe = ({ recipeId }) => {
    const deleteRecipe = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_API_URL}/recipe/delete/${recipeId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization' : localStorage.getItem('accessToken')
                }
            });
            if (response.ok) {
                alert('Recipe deleted successfully!');
                // You can call a callback here if needed
            } else {
                throw new Error('Failed to delete recipe');
            }
        } catch (error) {
            console.error('Error deleting recipe:', error);
            alert('Error deleting recipe');
        }
    };

    return (
        <button onClick={deleteRecipe}>
            레시피 삭제하기
        </button>
    );
};

export default DeleteRecipe;
