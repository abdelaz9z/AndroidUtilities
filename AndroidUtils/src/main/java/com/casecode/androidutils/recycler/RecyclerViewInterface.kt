package com.hyperone.packingapp.utils.recycler

/**
 * The interface Recycler view interface.
 *
 * @author Abdelaziz Daoud
 * @version 1.0
 * @since 2022-11-23
 */
interface RecyclerViewInterface {
    /**
     * On item click.
     *
     * @param position the position
     */
    fun onItemClick(position: Int)

    /**
     * On click listener.
     *
     * @param position the position
     */
    fun onClickListener(position: Int)

    /**
     * On long click listener.
     *
     * @param position the position
     */
    fun onLongClickListener(position: Int)
}