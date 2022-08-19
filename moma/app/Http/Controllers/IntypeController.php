<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\User;
use App\Models\Income;
use App\Models\Intype;
use App\Models\Outcome;
use App\Models\Outtype;
use Illuminate\Support\Facades\DB;

class IntypeController extends Controller
{
    //
    public function getType(Request $request)
    {
        $userid = $request->user_id;
        $intype = Intype::where(["user_id" => $userid])->get();
        
        if(isset($intype))
        {
            return response()->json([
                'success'=>true,
                'intype'=> $intype,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'intype'=> null,
            ]);
        }
    }

    public function addType(Request $request)
    {
        $intype = new Intype();

        $intype->intype_name = $request->intype_name;
        $intype->user_id = $request->user_id;

        $intype->save();

        $list = Intype::where(["user_id" =>$request->user_id])->get();
        return response()->json([
            'success'=>true,
            'intype'=> $list,
        ]);
    }
}
