#include "GTUContainer.h"

namespace GTUSTL
{
	template <class T> // No parameter constructor of GTUContainer
	GTUContainer<T>::GTUContainer()
	{
		this -> capacity_ = 1;
		this -> used_ = 0;
		shared_ptr <T> temp (new T[this -> capacity_], default_delete <T[]> ());
		this -> data_ = temp;
	}

	template <class T> // Copy constructor of GTUContainer
	GTUContainer<T>::GTUContainer(const GTUContainer& copyObject)
	{
		int capacityy = copyObject.capacity_;
		int usedd = copyObject.used_;

		shared_ptr<T> temp (new T[capacityy]);

		for(int i = 0 ; i < usedd ; ++i)
			(this -> data_).get()[i] = copyObject.data_.get()[i];
	}

	template <class T> // Destructor of GTUContainer
	GTUContainer<T>::~GTUContainer()
	{
		this -> data_ = nullptr;
		this -> used_ = 0;
		this -> capacity_ = 0;
	}

	template <class T>
	GTUContainer<T>& GTUContainer<T>::operator =(const GTUContainer& rightSide)
	// Assingment operator of GTUContainer
	{
		if(this == &rightSide)
			return (*this);

		this -> used_ = rightSide.used_;
		this -> capacity_ = rightSide.capacity_;

		shared_ptr<T> temp(new T[this -> capacity_]);

		this -> data_ = temp;

		for(int i = 0 ; i < this -> used_ ; ++i)
			(this -> data_).get()[i] = rightSide.data_.get()[i];

		return (*this);
	}
}