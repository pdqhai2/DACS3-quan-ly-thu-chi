<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Intype extends Model
{
    use HasFactory;

    protected $fillable = [
        'intype_id',
        'intype_name',
        'user_id',
    ];

    protected $hidden = [];
}
