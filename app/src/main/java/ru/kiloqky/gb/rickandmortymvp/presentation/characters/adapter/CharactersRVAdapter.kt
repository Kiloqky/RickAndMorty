package ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.kiloqky.gb.rickandmortymvp.databinding.CharacterItemBinding
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.imageloader.IImageLoader

class CharactersRVAdapter(
    private val presenter: ICharactersListPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<CharactersRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root), CharacterItemView {

        override var pos = -1

        override fun setInfo(character: Character) {
            binding.gender.text = character.gender?.name
            binding.name.text = character.name
            binding.species.text = character.species
            binding.status.text = character.status?.name
            character.imageUrl?.let {
                imageLoader.loadInto(
                    it,
                    binding.avatar
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int =
        presenter.getCount()

}